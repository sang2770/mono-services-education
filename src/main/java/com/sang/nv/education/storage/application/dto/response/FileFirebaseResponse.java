package com.sang.nv.education.storage.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileFirebaseResponse {
    private String fileName;
    private String fileViewUrl;
    private String originFileName;
}
