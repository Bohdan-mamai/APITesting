package pages;

import lombok.*;

@Data
@Builder(toBuilder = true)
public class Order {
    @NonNull
    public int petId;
    @NonNull
    public int quantity;
    @NonNull
    public Integer id;
    @NonNull
//    public String shipDate;
    public boolean complete;
    public String status;
}

