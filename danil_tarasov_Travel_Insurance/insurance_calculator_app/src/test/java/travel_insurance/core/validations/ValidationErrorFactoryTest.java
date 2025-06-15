package travel_insurance.core.validations;

/*

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travel_insurance.core.api.dto.ValidationErrorDTO;
import travel_insurance.core.util.ErrorCodeUtil;
import travel_insurance.core.util.Placeholder;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory factory;

    @Test
    public void shouldReturnValidationErrorWithDescription() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");
        ValidationErrorDTO error = factory.buildError("ERROR_CODE");
        Assert.assertEquals(error.getErrorCode(), "ERROR_CODE");
        Assert.assertEquals(error.getDescription(), "error description");
    }

    @Test
    public void shouldReturnValidationErrorWithDescriptionUsingPlaceholder() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "AAA");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error AAA description");
        ValidationErrorDTO error = factory.buildError("ERROR_CODE", List.of(placeholder));
        Assert.assertEquals(error.getErrorCode(), "ERROR_CODE");
        Assert.assertEquals(error.getDescription(), "error AAA description");
    }

}
*/
