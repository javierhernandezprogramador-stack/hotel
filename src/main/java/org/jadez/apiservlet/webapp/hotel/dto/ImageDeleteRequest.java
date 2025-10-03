package org.jadez.apiservlet.webapp.hotel.dto;

import java.util.List;

public class ImageDeleteRequest {
    private List<ImageDto> images;

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }
}
