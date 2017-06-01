package jszczepankiewicz.openpojo;


import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.BusinessKeyMustExistRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.BusinessIdentityTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static com.openpojo.reflection.impl.PojoClassFactory.getPojoClassesRecursively;
import static java.util.Optional.ofNullable;

/**
 * @author jszczepankiewicz
 * @since 2017-06-01
 */
public class PojoQualityTest {

  private static List<PojoClass> pojos;

  private static PojoClassFilter newAnnotationXClassFilter(){
    return pojoClass -> {
      Optional<AnnotationX> testGetterSetterAnnotation = ofNullable(pojoClass.getAnnotation(AnnotationX.class));
      return testGetterSetterAnnotation.isPresent();
    };
  }

  @BeforeClass
  public static void setUp() {
    pojos = getPojoClassesRecursively("jszczepankiewicz.openpojo", newAnnotationXClassFilter());
    System.out.println("Number of loaded business annotations: " + pojos.size());
  }

  @Test
  public void validateAnnotatedClassesAreAnnotationAndBusinessFriendly() {

    Validator validator = ValidatorBuilder.create()
            // Add Rules to validate structure for POJO_PACKAGE
            // See com.openpojo.validation.rule.impl for more ...
            .with(new GetterMustExistRule())
            .with(new SetterMustExistRule())
            //.with(new BusinessKeyMustExistRule())
            // Add Testers to validate behaviour for POJO_PACKAGE
            // See com.openpojo.validation.test.impl for more ...
            .with(new SetterTester())
            .with(new GetterTester())
            .with(new BusinessIdentityTester())
            .build();

        validator.validate(pojos);
  }

  @AfterClass
  public static void dispose() {
    pojos = null;
  }

}