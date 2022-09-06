package ph.matt.activitylogger.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProductEvent {

    @Id
    private String id;

    // @NonNull
    // private String httpMethod;
    @NonNull
    private String message;

}
