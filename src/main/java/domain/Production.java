package domain;

import java.util.List;

public class Production {
    private int productionId;
    private String title;
    private String genre;
    private int episodeNumber;
    private String producedBy;
    private List<Credit> creditList;

    public Production(int productionId, String title, String genre, int episodeNumber, String producedBy, List<Credit> creditList) {
        this.productionId = productionId;
        this.title = title;
        this.genre = genre;
        this.episodeNumber = episodeNumber;
        this.producedBy = producedBy;
        this.creditList = creditList;
    }
}
