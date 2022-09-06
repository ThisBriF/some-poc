package ph.matt.productmsvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEvent {

    @NonNull
    private String httpMethod;
    @NonNull
    private String message;

}
