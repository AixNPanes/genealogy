package ws.daley.genealogy.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

public class Instruction
{
    private Class<?> clazz;
    public Class<?> getClazz() {return this.clazz;}
    public void setClazz(Class<?> clazz) {this.clazz = clazz;}
    
	private String attrName;
    public String getAttrName() {return this.attrName;}
    public void setAttrName(String attrName) {this.attrName = attrName;}

    private Field field;
    public Field getField() {return this.field;}
    public void setField(Field field) {this.field = field;}
    
    private Constructor<?> constructor;
    public Constructor<?> getConstructor() {return this.constructor;}
    public void setConstructor(Constructor<?> constructor) {this.constructor = constructor;}
    
    private Method method;
    public Method getMethod() {return this.method;}
    public void setMethod(Method method) {this.method = method;}
    
    private boolean process;
    public boolean isProcess() {return this.process;}
    public void setProcess(boolean process) {this.process = process;}
    
	public Instruction(String attrName, String fieldName)
	{
		this.process = true;
		RuntimeException e = new RuntimeException();
		e.fillInStackTrace();
		StackTraceElement[] elements = e.getStackTrace();
		StackTraceElement element = elements[1];
		String me = element.getClassName();
		try {
	        this.clazz = ClassLoader.getSystemClassLoader().loadClass(me);
        }
        catch (ClassNotFoundException e2) {
        	throw new RuntimeException(e2);
        }
		if (!me.equals(this.clazz.getName()))
			throw new RuntimeException("Class " + this.clazz.getName() + " not same as me(" + this.getClass().getName() + ")");
//		if (!attrName.toUpperCase().equals(attrName))
//			throw new RuntimeException("Attribute " + attrName + " not upper case + for " + this.getClass().getName());
		this.attrName = attrName;
		this.method = null;
		String setMethodName = "";
		String addMethodName = "";
		try
		{
			this.field = this.clazz.getDeclaredField(fieldName);
			setMethodName = getGetter("set", this.field.getName());
			addMethodName = getGetter("add", this.field.getName());
			java.lang.reflect.Type genericType = this.field.getGenericType();
			boolean genType = (genericType == null);
			boolean instanceOf = genType?false:(genericType instanceof ParameterizedType);
			int argLength = instanceOf?(((ParameterizedType)genericType).getActualTypeArguments().length):-1;
			boolean actualArgsLength = instanceOf?(((ParameterizedType)genericType).getActualTypeArguments().length != 1):false;
			boolean genericString = instanceOf?genericType.equals(String.class):false;
			if (genericType == null)
			{
				this.constructor = this.field.getType().getConstructor(new Class<?>[]{Node.class});
				this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{this.field.getType()});
			}
			else if (genericType instanceof ParameterizedType)
			{
				ParameterizedType parameterizedType = (ParameterizedType)genericType;
				int l = parameterizedType.getActualTypeArguments().length;
				if (l != 1)
				{
					this.constructor = this.field.getType().getConstructor(new Class<?>[]{Node.class});
					this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{this.field.getType()});
				}
				else if (genericType.equals(String.class))
				{
					this.constructor = this.field.getType().getConstructor(new Class<?>[]{String.class});
					this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{this.field.getType()});
				}
				else
				{
					Type argType = parameterizedType.getActualTypeArguments()[0];
					if (argType.equals(String.class))
					{
						Class<?> fieldClass = ((Class<?>)(((ParameterizedType)genericType).getActualTypeArguments())[0]);
						this.constructor = fieldClass.getConstructor(new Class<?>[]{String.class});
						this.method = this.clazz.getDeclaredMethod(addMethodName, new Class<?>[]{fieldClass});
					}
					else
					{
						Class<?> fieldClass = ((Class<?>)(((ParameterizedType)genericType).getActualTypeArguments())[0]);
						this.constructor = fieldClass.getConstructor(new Class<?>[]{Node.class});
						this.method = this.clazz.getDeclaredMethod(addMethodName, new Class<?>[]{fieldClass});
					}
				}
			}
			else if (genericType.equals(String.class))
			{
				this.constructor = this.field.getType().getConstructor(new Class<?>[]{String.class});
				this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{this.field.getType()});
			}
			else
			{
				int i = 0;
			}
			if (this.constructor == null)
				if (genericType == null || 
						(genericType instanceof ParameterizedType && ((ParameterizedType)genericType).getActualTypeArguments().length != 1) || genericType.equals(String.class))
				{
					if (genericType.equals(String.class))
						this.constructor = this.field.getType().getConstructor(new Class<?>[]{String.class});
					else
						this.constructor = this.field.getType().getConstructor(new Class<?>[]{Node.class});
					this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{this.field.getType()});
				}
				else if (genericType instanceof ParameterizedType)
				{
					Class<?> fieldClass = ((Class<?>)(((ParameterizedType)genericType).getActualTypeArguments())[0]);
					this.constructor = fieldClass.getConstructor(new Class<?>[]{Node.class});
					this.method = this.clazz.getDeclaredMethod(addMethodName, new Class<?>[]{fieldClass});
				}
				else if (genericType instanceof Class<?>)
				{
					Class<?> fieldClass = (Class<?>)genericType;
					this.constructor = fieldClass.getConstructor(new Class<?>[]{Node.class});
					this.method = this.clazz.getDeclaredMethod(setMethodName, new Class<?>[]{fieldClass});
				}
				else
				{
					throw new RuntimeException("error getting instance class");
				}
		}
		catch(Exception e1) {throw new RuntimeException(e1);}
		if (this.constructor == null)
			throw new RuntimeException("X");
		if (this.method == null)
			throw new RuntimeException("Y");
	}
	
	private String getGetter(String get, String name)
	{
		String Name = get + name.substring(0, 1).toUpperCase();
		if (name.length() > 1)
			Name += name.substring(1);
		return Name;
	}
	
	public static void buildChildren(Object thisPtr, Instruction[] instructions, HashMap<Integer, Node> childMap)
	{
		HashMap<Integer, String> processed = new HashMap<Integer, String>();
		for(Map.Entry<Integer, Node> childEntry:childMap.entrySet())
		{
			Node child = childEntry.getValue();
			String childName = child.getNodeName();
			for(Instruction instruction:instructions)
			{
				if (instruction.getAttrName().equals(childName))
				{
					if ("CALN".equals(childName))
					{
						throw new RuntimeException("CALN not handled");
					}
					processed.put(childEntry.getKey(), "");
					try
					{
						Object o = instruction.getConstructor().newInstance(child);
						instruction.method.invoke(thisPtr, o);
					}
					catch (Exception e) {}
				}
			}
		}
		for(Map.Entry<Integer, String> processEntry: processed.entrySet())
		{
			if (childMap.get(processEntry.getKey()) != null)
				childMap.remove(processEntry.getKey());
		}
	}
}
