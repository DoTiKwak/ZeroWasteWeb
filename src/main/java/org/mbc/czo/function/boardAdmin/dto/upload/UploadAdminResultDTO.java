/*
package org.mbc.czo.function.boardAdmin.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadAdminResultDTO {

    private String uuid;
    private String fileName;
    private boolean img;
    public String getLink(){
        if(img){
            return "c_" + uuid + "_" + fileName;
        } else {
            return uuid + "_" + fileName;
        }
    }

}
*/
