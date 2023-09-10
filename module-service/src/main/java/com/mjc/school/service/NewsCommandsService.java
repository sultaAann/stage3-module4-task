package com.mjc.school.service;


import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.query.NewsQueryParams;

import java.util.List;

public interface NewsCommandsService {
    List<NewsDTOResponse> readBySearchParams(NewsQueryParams QueryParams);

}

