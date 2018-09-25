package com.easyLink.easyLink;

public class Linker {

        private String id;
        private String full_url;

    public Linker() {
    }

    public Linker(String id, String full_url) {
            this.id = id;
            this.full_url=full_url;
        }

        public String getID() {
            return id;
        }

        public String getFull_url() {
            return full_url;
        }

    @Override
    public String toString() {
        return "Linker{" +
                "id='" + id + '\'' +
                ", full_url='" + full_url + '\'' +
                '}';
    }
}
