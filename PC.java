
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PC is a computer which is used in homes and offices
 *
 * @attributes
 *
 *             <pre>
 * 		model 			String
 * 		year 			Integer
 * 		manufacturer 	String
 * 		comps 			Set<String>
 *
 * @object
 *
 *         <pre>
 * 		A typical PC is p=<m,y,m,c>,
 * 		where 	model(m)		,year(y)	,
 * 				manufacturer(m)	,comps(c)
 *         </pre>
 *
 * @abstract_properties
 *
 *                      <pre>
 * 	mutable(model)=true 		/\ optional(model)=false 		/\ length(model)=20 			/\
 * 	mutable(year)=false 		/\ optional(year)=false 		/\ min(year) = 1940				/\
 * 	mutable(manufacturer)=false /\ optional(manufacturer)=false /\ length(manufacturer) = 20	/\
 * 	mutable(comps)=true 		/\ optional(comps)=false
 *                      </pre>
 *
 */
public class PC {
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 20)
    private String model;
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1940)
    private int year;
    @DomainConstraint(type = "String", mutable = false, optional = false, length = 20)
    private String manufacturer;
    @DomainConstraint(type = "Set<String>", mutable = true, optional = false)
    private Set<String> comps;


    /**
     *
     * @effects
     *
     *          		<pre>
     *          if model, year, manufacturer, comps
     *          	are validated, initialize this as <model, year, manufacturer, comps>
     *          else throws NotPossibleException
     *                  </pre>
     */
    @DOpt(type = OptType.Constructor)
    public PC(@AttrRef("model")String model,
              @AttrRef("year")int year,
              @AttrRef("manufacturer")String manufacturer,
              @AttrRef("comps")Set<String> comps) throws NotPossibleException {
        if (!validateModel(model))
            throw new NotPossibleException("PC.init: Invalid model:" + model);
        if (!validateYear(year))
            throw new NotPossibleException("PC.init: Invalid year:" + year);
        if (!validateManufacturer(manufacturer))
            throw new NotPossibleException("PC.init: Invalid manufacturer:" + manufacturer);
        if (!validateComps(comps))
            throw new NotPossibleException("PC.init: Invalid comps:" + comps);
        this.model = model;
        this.year = year;
        this.manufacturer = manufacturer;
        this.comps = comps;
    }


    /**
     * @effects
     *
     *          <pre>
     * if manufacturer is valid
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("manufacturer")
    public boolean validateManufacturer(String manufacturer) {
        return manufacturer != null && !manufacturer.isEmpty()&& manufacturer.length()<=20;
    }

    /**
     * @effects
     *
     *          <pre>
     * if year is valid
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("year")
    public boolean validateYear(int year) {
        return year >= 1940;
    }


    /**
     * @effects
     * return <tt>comps</tt>
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("comps")
    public Set<String> getComps() {
        return comps;
    }

    /**
     * @effects
     *
     *          <pre>
     * if comps is valid
     * 		set this.comps=comps
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("comps")
    public boolean setComps(Set<String> comps) {
        if (!validateComps(comps)) {
            return false;
        }
        this.comps = comps;
        return true;
    }

    /**
     * @effects
     *
     *          <pre>
     * if comps is valid
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("comps")
    public boolean validateComps(Set<String> comps) {
        return comps !=null;
    }


    /**
     * @effects
     * return <tt>model</tt>
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("model")
    public String getModel() {
        return model;
    }


    /**
     * @effects
     *
     *          <pre>
     * if model is valid
     * 		set this.model=model
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("model")
    public boolean setModel(String model) {
        if (!validateModel(model))
            return false;
        this.model = model;
        return true;
    }

    /**
     * @effects
     *
     *          <pre>
     * if model is valid
     * 		return true
     * else
     * 		return false
     *          </pre>
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("model")
    public boolean validateModel(String model) {
        return model!= null && model.length()<=20 && !model.isEmpty();
    }

    /**
     * @effects
     * return <tt>year</tt>
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("year")
    public int getYear() {
        return year;
    }

    /**
     * @effects
     * return <tt>manufacturer</tt>
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }


    /**
     * @effects
     *
     *          <pre>
     *          return the data contains all properties of PC
     */
    @DOpt(type = OptType.Default)
    @Override
    public String toString() {
        return "PC:<" +
                model + "," +
                year + "," +
                manufacturer + "," +
                comps.toString() +
                ">";
    }


    /**
     * @effects
     *	if the properties are equal
     *     	return true
     *  else
     *  	return false
     */
    @DOpt(type = OptType.Default)
    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof PC)) return false;
        PC pc = (PC) that;
        return getYear() == pc.getYear() &&
                getModel().equals(pc.getModel()) &&
                getManufacturer().equals(pc.getManufacturer()) &&
                getComps().equals(pc.getComps());

    }

    /**
     * @effects
     * if this satisfies abstract properties
     * 		return true
     * else
     * 		return false
     */
    public boolean repOK(Object that) {
        if (that == null)
            return false;

        return true;
    }
}
