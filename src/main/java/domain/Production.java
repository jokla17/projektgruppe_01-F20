package domain;

public class Production {
    private String productionId;
    private String title;
    private String genre;
    private int episodeNumber;
    private int productionYear;
    private String productionCountry;
    private String producedBy;

    public Production(String productionId, String title, String genre, int episodeNumber, int productionYear, String productionCountry, String producedBy) {
        this.productionId = productionId;
        this.title = title;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.producedBy = producedBy;
    }

    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = "P" + productionId;
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

    @Override
    public String toString() {
        return productionId + ";" + title + ";" + genre + ";" + episodeNumber + ";" + productionYear + ";" + productionCountry + ";" + producedBy;
    }
}
