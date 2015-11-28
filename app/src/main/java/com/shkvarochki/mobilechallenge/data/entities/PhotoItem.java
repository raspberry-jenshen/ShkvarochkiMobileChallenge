package com.shkvarochki.mobilechallenge.data.entities;

import com.annimon.stream.Optional;

public class PhotoItem {
    private final Optional<String> uri;

    public PhotoItem() {
        this.uri = Optional.empty();
    }

    public PhotoItem(String uri) {
        this.uri = Optional.of(uri);
    }

    public Optional<String> getUri() {
        return uri;
    }
}
