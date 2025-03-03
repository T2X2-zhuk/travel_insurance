package travel_insurance.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoreResponse {

    private List<ValidationMistake> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
