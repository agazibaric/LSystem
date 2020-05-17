package hr.fer.zemris.lsystems.impl;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.math.Vector2D;

/**
 * This class is used for configuring and building new <code>LSystem</code> object. </br>
 * It can be configured through methods that it offers </br>
 * or with given textual input that represents its configuration.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder {
	
	/**
	 * length of the line
	 */
	private double unitLength = 0.1;
	/**
	 * scaler used for modifying unit length of the line
	 */
	private double unitLengthDegreeScaler = 1;
	/**
	 * position where fractal pattern starts
	 */
	private Vector2D origin = new Vector2D(0, 0);
	/**
	 * angle of the pattern that is drawn
	 */
	private double angle = 0;
	/**
	 * axiom that represents base of fractal pattern
	 */
	private String axiom = "";
	/**
	 * represents map-like storage of commands
	 */
	private Dictionary commands = new Dictionary();
	/**
	 * represents map-like storage of productions
	 */
	private Dictionary productions = new Dictionary();
	
	/**
	 * Constructor for creating new <code>LSystemBuilderImpl</code> object.
	 * It accepts no arguments.
	 */
	public LSystemBuilderImpl() {
		super();
	}
	
	/**
	 * Private class that represents implementation of <code>LSystem</code>.
	 * 
	 * @author Ante Gazibarić
	 * @version 1.0
	 *
	 */
	private class LSystemimpl implements LSystem {
		
		@Override
		public void draw(int level, Painter painter) {
			Context ctx = new Context();
			Vector2D direction = new Vector2D(1, 0);
			direction.rotate(angle);
			Color color = Color.BLACK;
			double lengthOfLine = unitLength * Math.pow(unitLengthDegreeScaler, level);
			Vector2D originPoint = new Vector2D(origin.getX(), origin.getY());
			TurtleState turtle = new TurtleState(originPoint, direction, color, lengthOfLine);
			ctx.pushState(turtle);
			String fractalString = generate(level);
			char[] fractalChars = fractalString.toCharArray();
			
			for (char c : fractalChars) {
				Command command = (Command) commands.get(c);
				if (command != null)
					command.execute(ctx, painter);
			}
		}

		@Override
		public String generate(int level) {
			if(level < 0)
				throw new IllegalArgumentException("Level must not be negative. You entered: " + level);
			if (level == 0) 
				return axiom;
			
			StringBuilder productionBuilder = new StringBuilder();
			char[] parts = generate(level - 1).toCharArray();
			for (char part : parts) {
				String production = (String) productions.get(part);
				productionBuilder.append(production == null ? part : production);
			}
			
			return productionBuilder.toString();
		}
	}
	
	/**
	 * Method that returns new <code>LSystem</code> object configured by this <code>LSystemBuilderImpl</code>.
	 */
	@Override
	public LSystem build() {
		return new LSystemimpl();
	}

	/**
	 * Method used for adding new command. </br>
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder registerCommand(char symbol, String commandText) {
		Command command = getCommand(commandText);
		commands.put(symbol, command);
		return this;
	}

	/**
	 * Method used for adding production. </br>
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		productions.put(symbol, production);
		return this;
	}

	/**
	 * Method used for setting angle of fractal pattern.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}

	/**
	 * Method used for setting axiom of the fractal pattern.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder setAxiom(String axiom) {
		this.axiom = axiom;
		return this;
	}

	/**
	 * Method used for setting starting position of the fractal pattern.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		origin = new Vector2D(x, y);
		return this;
	}

	/**
	 * Method used for setting unit length of the fractal pattern.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder setUnitLength(double unithLength) {
		this.unitLength = unithLength;
		return this;
	}

	/**
	 * Method used for setting scaler of unit length of the fractal pattern.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}
	
	/**
	 * Method used for configuring LSystem from text.
	 * It returns <code>this</code> object.
	 */
	@Override
	public LSystemBuilder configureFromText(String[] lines) {
		
		for (String line : lines) {
			if (line.contains("origin")) {
				setOriginFromString(line);
			} else if (line.contains("angle")) {
				setAngleFromString(line);
			} else if (line.contains("unitLengthDegreeScaler")) {
				setUnitLengthDegreeScalerFromString(line);
			} else if (line.contains("unitLength")) {
				setUnitLengthFromString(line);
			} else if (line.contains("axiom")) {
				setAxiomFromString(line);
			} else if (line.contains("command")) {
				addCommandFromString(line);
			} else if (line.contains("production")) {
				addProductionFromString(line);
			}
		}
		return this;
	}
	
	/**
	 * Method used for getting command from text.
	 * 
	 * @param commandText <code>String</code> that contains command
	 * @return   		  <code>Command</code> that given text represents
	 */
	private static Command getCommand(String commandText) {
		Command command = null;
		if (commandText.contains("draw")) {
			command = getDrawCommand(commandText);
		} else if (commandText.contains("rotate")) {
			command = getRotateCommand(commandText);
		} else if (commandText.contains("skip")) {
			command = getSkipCommand(commandText);
		} else if (commandText.contains("push")) {
			command = getPushCommand();
		} else if (commandText.contains("pop")) {
			command = getPopCommand();
		} else if (commandText.contains("scale")) {
			command = getScaleCommand(commandText);
		} else if (commandText.contains("color")) {
			command = getColorCommand(commandText);
		} else {
			throw new IllegalArgumentException("Unsupported command! Command was: " + commandText);
		}
		return command;
	}

	/**
	 * Method used for getting <code>DrawCommand</code> from text.
	 * 
	 * @param commandText <code>String</code> that contains draw command
	 * @return            <code>DrawCommand</code> that given text represents
	 */
	private static DrawCommand getDrawCommand(String commandText) {
		try {
			double step = Double.parseDouble(commandText.split("\\s+")[1]);
			return new DrawCommand(step);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid Draw Command!");
		}
	}
	
	/**
	 * Method used for getting <code>RotateCommand</code> from text.
	 * 
	 * @param commandText <code>String</code> that contains rotate command
	 * @return            <code>RotateCommand</code> that given text represents
	 */
	private static RotateCommand getRotateCommand(String commandText) {
		try {
			double angle = Double.parseDouble(commandText.split("\\s+")[1]);
			return new RotateCommand(angle);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid Rotate Command!");
		}
	}
	
	/**
	 * Method used for getting <code>SkipCommand</code> from text.
	 * 
	 * @param commandText <code>String</code> that contains skip command
	 * @return            <code>SkipCommand</code> that given text represents
	 */
	private static SkipCommand getSkipCommand(String commandText) {
		try {
			double step = Double.parseDouble(commandText.split("\\s+")[1]);
			return new SkipCommand(step);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid Skip Command!");
		}
	}
	
	/**
	 * Method used for getting push command.
	 * 
	 * @return <code>PushCommand</code>
	 */
	private static PushCommand getPushCommand() {
		return new PushCommand();
	}
	
	/**
	 * Method used for getting pop command.
	 * 
	 * @return <code>PopCommand</code>
	 */
	private static PopCommand getPopCommand() {
		return new PopCommand();
	}
	
	/**
	 * Method used for getting <code>ScaleCommand</code> from text.
	 * 
	 * @param commandText <code>String</code> that contains scale command
	 * @return            <code>ScaleCommand</code> that given text represents
	 */
	private static ScaleCommand getScaleCommand(String commandText) {
		try {
			double factor = Double.parseDouble(commandText.split("\\s+")[1]);
			return new ScaleCommand(factor);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid Scale Command!");
		}
	}
	
	/**
	 * Method used for getting <code>ColorCommand</code> from text.
	 * 
	 * @param commandText <code>String</code> that contains color command
	 * @return            <code>ColorCommand</code> that given text represents
	 */
	private static Command getColorCommand(String commandText) {
		try {
			String hexColor = commandText.split("\\s+")[1];
			Color color = Color.decode("#" + hexColor);
			return new ColorCommand(color);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid Color Command!");
		}
	}
	
	/**
	 * Method that sets origin which is given in text.
	 * 
	 * @param line <code>String</code> that represents origin
	 */
	private void setOriginFromString(String line) {
		String[] originParts = line.split("\\s+");
		try {
			double x = Double.parseDouble(originParts[1]);
			double y = Double.parseDouble(originParts[2]);
			setOrigin(x, y);
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid origin input. You entered: " + line);
		}
	}
	
	/**
	 * Method that sets angle which is given in text.
	 * 
	 * @param line <code>String</code> that represents angle
	 */
	private void setAngleFromString(String line) {
		String[] angleParts = line.split("\\s+");
		try {
			double angle = Double.parseDouble(angleParts[1]);
			setAngle(angle);
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid angle input. You entered: " + line);
		}
	}
	
	/**
	 * Method that sets unit length which is given in text.
	 * 
	 * @param line <code>String</code> that represents unit length
	 */
	private void setUnitLengthFromString(String line) {
		String[] unitLengthParts = line.split("\\s+");
		try {
			double unitLength = Double.parseDouble(unitLengthParts[1]);
			setUnitLength(unitLength);
		}  catch (NumberFormatException | IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid unit length input. You entered: " + line);
		}
	}
	
	/**
	 * Method that sets unit length degree scaler which is given in text.
	 * 
	 * @param line <code>String</code> that represents unit length degree scaler
	 */
	private void setUnitLengthDegreeScalerFromString(String line) {

		try {
			// Split line in two parts: name of parameter ("unitLengthDegreeScaler") and values
			String valueString = line.split("\\s+", 2)[1].replaceAll("\\s+", "");
			// Pattern for matching value given in fraction form
			Pattern fractionPattern = Pattern.compile("([0-9]+\\.?[0-9]*?)/([0-9]+\\.?[0-9]*?)$");
			Matcher fractionMatcher = fractionPattern.matcher(valueString);
			
			double scaler;
			if (fractionMatcher.find()) {
				double numerator = Double.parseDouble(fractionMatcher.group(1));
				double denominator = Double.parseDouble(fractionMatcher.group(2));
				scaler = numerator / denominator;
			} else {
				// If fraction pattern is not matched, then it's single double value
				scaler = Double.parseDouble(valueString);
			}
		setUnitLengthDegreeScaler(scaler);
		} catch (IndexOutOfBoundsException | NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid unit length degree scaler input. You entered: " + line);
		}
	}
	
	/**
	 * Method that sets axiom which is given in text.
	 * 
	 * @param line <code>String</code> that represents axiom
	 */
	private void setAxiomFromString(String line) {
		String[] axiomParts = line.split("\\s+");
		try {
			setAxiom(axiomParts[1]);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid axiom input. You entered: " + line);
		}
	}
	
	/**
	 * Method that adds command which is given in text.
	 * 
	 * @param line <code>String</code> that represents command
	 */
	private void addCommandFromString(String line) {
		String[] commandParts = line.split("\\s+");
		try {
			String command = "";
			for (int i = 2; i < commandParts.length; i++) {
				command = command.concat(commandParts[i] + " ");
			}
			registerCommand(commandParts[1].charAt(0), command);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid command input. You entered: " + line);
		}
	}
	
	/**
	 * Method that adds production which is given in text.
	 * 
	 * @param line <code>String</code> that represents production
	 */
	private void addProductionFromString(String line) {
		String[] productionParts = line.split("\\s+");
		try {
			registerProduction(productionParts[1].charAt(0), productionParts[2]);
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid production input. You entered: " + line);
		}
	}
	
}
