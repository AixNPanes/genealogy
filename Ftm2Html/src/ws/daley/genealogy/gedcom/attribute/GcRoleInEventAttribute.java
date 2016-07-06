package ws.daley.genealogy.gedcom.attribute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * ROLE_IN_EVENT:=	{Size=1:15}
 * [ CHIL | HUSB | WIFE | MOTH | FATH | SPOU | (<ROLE_DESCRIPTOR>) ] 
 * 
 * Indicates what role this person played in the event that is being cited in 
 * this context. For example, if you cite a child's birth record as the source of 
 * the mother's name, the value for this field is "MOTH." If you describe the 
 * groom of a marriage, the role is "HUSB." If the role is something different 
 * than one of the six relationship role tags listed above then enclose the role 
 * name within matching parentheses.
 */

public class GcRoleInEventAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	private static Vector<Object> roles = new Vector<Object>();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ROLE_IN_EVENT", 1, 15, GcRoleInEventAttribute.class),
		});
		roles.add("CHIL");
		roles.add("HUSB");
		roles.add("WIFE");
		roles.add("MOTH");
		roles.add("FATH");
		roles.add("SPOU");
		roles.add(GcRoleDescriptorAttribute.class);
	}

	@Override
    public boolean interpret()
	{
		for(Object o:roles)
			if (o instanceof String)
				if (((String)o).equals(this.parameters))
					return true;
		for(Object o:roles)
			if (!(o instanceof String))
			{
				Class<?> clazz = (Class<?>)o;
				try{
					Constructor<?> constructor = clazz.getConstructor(new Class[]{});
					Object subType = constructor.newInstance(new Object[]{});
					Method method = clazz.getMethod("interpret", new Class[]{});
					method.invoke(subType, new Object[]{});
					return true;
				}
				catch(NoSuchMethodException e) {}
				catch(InvocationTargetException e) {}
				catch(InstantiationException e) {}
				catch(IllegalAccessException e) {}
			}
		return false;
	}
}
