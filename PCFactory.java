
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;


/**
 *
 *
 * 	@overview
 * 		PCFactory is a singleton class which is needed to create PC object
 *
 */
public class PCFactory {
    @DomainConstraint(type = "PCFactory", mutable = false, optional = false)
    private static PCFactory instance;

    @DOpt(type = OptType.Constructor)
    private PCFactory() {

    }

    @DOpt(type = OptType.Helper)
    @AttrRef("instance")
    public static PCFactory getInstance() {
        if (instance == null) {
            instance = new PCFactory();
        }
        return instance;
    }

    @DOpt(type = OptType.Helper)
    public PC createPC(String model, int year, String manufacturer, Set<String> comps) throws NotPossibleException {
        return new PC(model, year, manufacturer, comps);
    }
}
