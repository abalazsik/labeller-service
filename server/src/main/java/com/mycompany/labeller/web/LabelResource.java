package com.mycompany.labeller.web;

import com.mycompany.labeller.web.data.WebLabellerException;
import com.mycompany.labeller.web.data.WebUpdateLabel;
import com.mycompany.labeller.web.data.WebLabel;
import com.mycompany.labeller.web.data.WebCreateLabel;
import com.mycompany.labeller.commons.CachedLabelId;
import com.mycompany.labeller.domain.services.LabelService;
import com.mycompany.labeller.domain.data.Label;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.data.UpdateLabel;
import com.mycompany.labeller.domain.data.attributes.LabelClassifierData;
import com.mycompany.labeller.domain.data.attributes.LabelDescription;
import com.mycompany.labeller.domain.data.attributes.LabelName;
import com.mycompany.labeller.domain.data.attributes.LabelTechnical;
import com.mycompany.labeller.domain.data.attributes.LabelVersion;
import com.mycompany.labeller.domain.exceptions.LabellerException;
import com.mycompany.labeller.commons.security.SecurityUtil;
import com.mycompany.labeller.web.data.WebGetLabelsForString;
import com.mycompany.labeller.web.data.WebLabelInfo;
import com.mycompany.labeller.web.mapper.WebLabelMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ador
 */
@RestController
@RequestMapping(path = "/api/labels")
public class LabelResource {

    @Autowired
    private LabelService labelService;

    @Autowired
    private LabelExporter labelExporter;

    @Operation(description = "Get a label by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<WebLabel> getById(@PathParam("id") Long id) {
        Optional<Label> label = labelService.getById(CachedLabelId.of(id), SecurityUtil.getUser());
        if (label.isPresent()) {
            return ResponseEntity.ok(WebLabelMapper.fromDomain(label.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "List all labels")
    @GetMapping(path = "/all")
    public List<WebLabelInfo> all(
            @RequestParam(name = "from", required = false, defaultValue = "0") int from,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        return labelService.getAll(new LabelRange(from, limit), SecurityUtil.getUser())
                .map(WebLabelMapper::toInfo).collect(Collectors.toList());
    }

    @Operation(description = "Create new label")
    @PostMapping
    public Long create(WebCreateLabel createLabel) {
        return labelService.create(WebLabelMapper.toDomainCreate(createLabel), SecurityUtil.getUser()).getValue();
    }

    @Operation(description = "Update a label")
    @PutMapping(path = "/{id}")
    public void update(@PathParam("id") Long id, WebUpdateLabel updateLabel) {
        labelService.update(
                new UpdateLabel(CachedLabelId.of(id),
                        new LabelName(updateLabel.getName()),
                        new LabelDescription(updateLabel.getDescription()),
                        new LabelClassifierData(updateLabel.getClassifierData()),
                        LabelTechnical.of(updateLabel.isTechnical()),
                        CachedLabelId.of(updateLabel.getParent()),
                        new LabelVersion(updateLabel.getVersion())
                ),
                SecurityUtil.getUser());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathParam("id") Long id) {
        labelService.delete(CachedLabelId.of(id), SecurityUtil.getUser());
    }

    @GetMapping(path = "/export", produces = "application/vnd.ms-excel")
    public void export(HttpServletResponse httpServletResponse) {
        try {
            labelExporter.exportAll(httpServletResponse.getOutputStream());
            httpServletResponse.setStatus(200);
        } catch (Exception e) {
            httpServletResponse.setStatus(500);
        }
    }

    @PostMapping(path = "/forString")
    public List<WebLabel> getLabelsForString(WebGetLabelsForString text) {
        return labelService.getLabelsForString(WebLabelMapper.toDomainGetLabelsForString(text), SecurityUtil.getUser())
                .map(WebLabelMapper::fromDomain).collect(Collectors.toList());
    }

    @ExceptionHandler(LabellerException.class)
    public ResponseEntity<WebLabellerException> handleException(LabellerException ex) {
        return ResponseEntity
                .status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new WebLabellerException(ex.getMessage()));
    }

}
