package com.cy.summary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.summary.entity.summary;

public interface SummaryService extends IService<summary> {
    void saveSummary(summary summary);
}
