package com.mycompany.labeller.commons.roles;

import com.mycompany.labeller.domain.user.IUser;
import com.mycompany.labeller.domain.user.Rights;

/**
 *
 * @author ador
 */
public class Roles {

    public static final IUser Anonymus = new LabellerRole(Rights.LabelGetAll, Rights.LabelGetClassified, Rights.LabelGetById);
    public static final IUser Auditor = new LabellerRole(Rights.LabelGetAll, Rights.LabelGetClassified, Rights.LabelGetById, Rights.LabelAUDIT);
    public static final IUser Admin = (String right) -> true;

}
