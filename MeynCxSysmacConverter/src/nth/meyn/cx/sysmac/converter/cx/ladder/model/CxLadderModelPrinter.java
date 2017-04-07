package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.FB;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.FBPARAMETER;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.HORIZONTAL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand;

public class CxLadderModelPrinter {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\r";
	private final int maxTextWidth;
	private final CxLadderModel cxLadderModel;

	public CxLadderModelPrinter(CxLadderModel cxLadderModel, int maxTextWidth) {
		this.cxLadderModel = cxLadderModel;
		this.maxTextWidth = maxTextWidth;
	}

	public String print() {
		StringBuilder result = new StringBuilder();
		String comment = cxLadderModel.getComment();
		if (comment != null && !comment.isEmpty()) {
			result.append(comment);
			result.append(NEW_LINE);
		}
		int maxX = cxLadderModel.getMaxX();
		int maxY = cxLadderModel.getMaxY();
		for (int y = 0; y <= maxY; y++) {
			boolean lineWasEmpty = false;
			int lineNr = 0;
			while (!lineWasEmpty) {
				lineNr++;
				StringBuilder lineText = new StringBuilder();
				for (int x = 0; x <= maxX; x++) {
					String cellText = print(x, y, lineNr);
					lineText.append(cellText);
				}
				lineWasEmpty = lineText.toString().replace("|", "").trim().length() == 0;
				if (!lineWasEmpty) {
					result.append(lineText);
					result.append(NEW_LINE);
				}
			}
		}
		return result.toString();
	}

	public String print(int x, int y, int line) {
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.FB }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.COIL }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.FBPARAMETER }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.VERTICAL }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.CONTACT }{@code >}
		// * {@link JAXBElement }{@code <}{@link
		// CxLadderDiagram.RungList.RUNG.ElementList.HORIZONTAL }{@code >}
		Object value = cxLadderModel.get(x, y);
		String text;
		if (value == null) {
			text = printEmpty();
		} else if (value instanceof FB) {
			text = print((FB) value, line);
		} else if (value instanceof COIL) {
			text = print((COIL) value, line);
		} else if (value instanceof INSTRUCTION) {
			text = print((INSTRUCTION) value, line);
		} else if (value instanceof FBPARAMETER) {
			text = print((FBPARAMETER) value, line);
		} else if (value instanceof CONTACT) {
			text = print((CONTACT) value, line);
		} else if (value instanceof HORIZONTAL) {
			text = print((HORIZONTAL) value, line);
		} else {
			throw new RuntimeException("Could not print cell x:" + x + " y:" + y);
		}
		return printWithVertical(text, x, y, line);

	}

	private String printWithVertical(String text, int x, int y, int line) {
		boolean hasVerticalFromAbove = cxLadderModel.hasVertical(x, y - 1);
		boolean hasVerticalToBelow = cxLadderModel.hasVertical(x, y);
		if (line == 1) {
			if (hasVerticalFromAbove) {
				return "|" + text;
			} else {
				return " " + text;
			}
		} else if (line == 2) {
			if (hasVerticalFromAbove || hasVerticalToBelow) {
				return "+" + text;
			} else {
				if (text.trim().length() == 0) {
					return " " + text;
				} else {
					return "-" + text;
				}
			}
		} else {// line > 2
			if (hasVerticalToBelow) {
				return "|" + text;
			} else {
				return " " + text;
			}
		}
	}

	private String printEmpty() {
		return repeat(SPACE, maxTextWidth);
	}

	private String print(HORIZONTAL value, int line) {
		if (line == 2) {
			return repeat("-", maxTextWidth);
		} else {
			return repeat(SPACE, maxTextWidth);
		}
	}

	private String print(CONTACT contact, int line) {

		if (line == 1) {
			return trimAndCenter(contact.getOperands().getOperand().getName(), maxTextWidth);
		} else if (line == 2) {
			StringBuilder text = new StringBuilder();
			int dashesBefore = (maxTextWidth - 5) / 2;
			text.append(repeat("-", dashesBefore));
			text.append("|");
			if (contact.getNegated() == 1) {
				text.append("/");
			} else {
				text.append(SPACE);
			}
			if (contact.getImmRefresh() == 1) {
				text.append("|");
			} else {
				text.append(SPACE);
			}
			if (contact.getDiffUp() == 1) {
				text.append("^");
			} else if (contact.getDiffDown() == 1) {
				text.append("v");
			} else {
				text.append(SPACE);
			}
			text.append("|");
			int dashesAfter = maxTextWidth - 5 - dashesBefore;
			text.append(repeat("-", dashesAfter));
			return text.toString();
		} else {
			return repeat(SPACE, maxTextWidth);
		}
	}

	private String padRight(String s, int length) {
		return s + repeat(SPACE, length);
	}

	private String padLeft(String s, int length) {
		return repeat(SPACE, length) + s;
	}

	private String repeat(String s, int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(s);
		}
		return result.toString();
	}


	private String trimAndCenter(String text, int width) {
		if (text == null) {
			text = "";
		} else if (text.length() > width) {
			return text.substring(0, width);
		}

		int padLeft = (width - text.length()) / 2;
		int padRight = width - text.length() - padLeft;
		String result = padRight(padLeft(text, padLeft), padRight);
		return result;

	}


	private String print(FBPARAMETER value, int line) {
		// TODO Auto-generated method stub
		return null;
	}

	private String print(INSTRUCTION instruction, int line) {

		List<Operand> operands = instruction.getOperands().getOperand();
		if (line == 1 || line == operands.size() + 2) {
			return "+" + repeat("-", maxTextWidth - 2) + "+";
		} else if (line > 1 && line < operands.size() + 2) {
			return "|" + trimAndCenter(operands.get(line - 2).getName(), maxTextWidth - 2) + "|";

		} else {
			return repeat(SPACE, maxTextWidth);
		}
	}

	private String print(COIL coil, int line) {

		if (line == 1) {
			return trimAndCenter(coil.getOperands().getOperand().getName(), maxTextWidth);
		} else if (line == 2) {
			StringBuilder text = new StringBuilder();
			int dashesBefore = (maxTextWidth - 5) / 2;
			text.append(repeat("-", dashesBefore));
			text.append("(");
			if (coil.getNegated() == 1) {
				text.append("/");
			} else {
				text.append(SPACE);
			}
			if (coil.getImmRefresh() == 1) {
				text.append("|");
			} else {
				text.append(SPACE);
			}
			if (coil.getDiffUp() == 1) {
				text.append("^");
			} else if (coil.getDiffDown() == 1) {
				text.append("v");
			} else {
				text.append(SPACE);
			}
			text.append(")");
			int dashesAfter = maxTextWidth - 5 - dashesBefore;
			text.append(repeat("-", dashesAfter));
			return text.toString();
		} else {
			return repeat(SPACE, maxTextWidth);
		}
	}

	private String print(FB value, int line) {
		// TODO Auto-generated method stub
		return null;
	}

}
