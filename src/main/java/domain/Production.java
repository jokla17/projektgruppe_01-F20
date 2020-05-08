package domain;

public class Production {
    private int productionId;
    private String title;
    private String genre;
    private int episodeNumber;
    private int productionYear;
    private String productionCountry;
    private String producedBy;
    private int producerId;

    public Production(int productionId, String title, String genre, int episodeNumber, int productionYear, String productionCountry, String producedBy) {
        this.productionId = productionId;
        this.title = title;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.producedBy = producedBy;
    }

    public Production(String title, String genre, int episodeNumber, int productionYear, String productionCountry, String producedBy) {
        this.title = title;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.producedBy = producedBy;
    }

    public Production(String title, String genre, int episodeNumber, int productionYear, String productionCountry, String producedBy, int producerId) {
        this.title = title;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.producedBy = producedBy;
        this.producerId = producerId;
    }

    public int getProductionId() {
        return productionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public void setProducedBy(String producedBy) {
        this.producedBy = producedBy;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return productionId + ";" + title + ";" + genre + ";" + episodeNumber + ";" + productionYear + ";" + productionCountry + ";" + producedBy;
    }
}
