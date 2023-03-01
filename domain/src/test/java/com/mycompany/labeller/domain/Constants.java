package com.mycompany.labeller.domain;

import com.mycompany.labeller.domain.services.TimeSource;
import com.mycompany.labeller.domain.user.IUser;
import com.mycompany.labeller.domain.user.Rights;
import java.time.LocalDateTime;

/**
 *
 * @author ador
 */
public class Constants {

    public static final IUser auditorUser = (right) -> right.equals(Rights.LabelGetById) || right.equals(Rights.LabelAUDIT);
    public static final IUser userWithGetByIdRight = (right) -> right.equals(Rights.LabelGetById);
    public static final IUser userWithDeleteRight = (right) -> right.equals(Rights.LabelDelete);
    public static final IUser rightless = (right) -> false;
    public static final IUser admin = (right) -> true;

    public static final TimeSource TEST_TIME_SOURCE = () -> LocalDateTime.now();
}
