package com.uimirror.common.annotation;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Deprecated
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = RuntimeException.class)
public @interface ReadCommitedReadOnlyTransaction {

}
