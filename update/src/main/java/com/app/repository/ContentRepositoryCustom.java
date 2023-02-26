package com.app.repository;

import java.util.List;

public interface ContentRepositoryCustom {
    void deleteAllContentQueryDsl(List<Long> contentIds);
}
