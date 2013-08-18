/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2013 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <michael[at]erichseifert.de>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */
package gui.graph;

import gui.forms.util.DateTool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.entity.inventorysheet.InventorySheet;
import common.entity.inventorysheet.InventorySheetData;

import util.DateFormatter;
import util.Utility;
import util.Values;

import de.erichseifert.gral.data.Column;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.graphics.DrawableContainer;
import de.erichseifert.gral.graphics.TableLayout;
import de.erichseifert.gral.plots.Plot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.areas.AreaRenderer;
import de.erichseifert.gral.plots.areas.DefaultAreaRenderer2D;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.Insets2D;

public class LinePlot extends ExamplePanel {
	/** Version id for serialization. */
	private static final long serialVersionUID = 6832343098989019088L;

	private String title;
	private String description;
	private String xAxisTitle;
	private String yAxisTitle;

	@SuppressWarnings("unchecked")
	public LinePlot(List<InventorySheetData> isds) {

		// better graph title appearance
		// x and y axes labels
		// show data(x, y) values when hovered

		// List<InventorySheetData> isds = new ArrayList<InventorySheetData>();
		List<InventorySheet> iss = new ArrayList<InventorySheet>();
		for (InventorySheetData isd : isds) {
			iss.add(new InventorySheet(isd));
		}

		DataTable data = new DataTable(Long.class, Double.class);
		for (InventorySheet is : iss) {
			data.add((DateTool.getDateWithoutTime(is.getInventorySheetData().getDate()).getTime() + DateTool.DAY_DIFFERENCE / 3),
					is.getOverallCashAndCheckSalesAmount() + is.getOverallAccountReceivables());
		}

		// Calendar d1 = Calendar.getInstance();
		// d1.set(Calendar.HOUR, 0);
		// d1.set(Calendar.YEAR, 2013);
		// d1.set(Calendar.MONTH, Calendar.APRIL);
		// d1.set(Calendar.DATE, 20);
		//
		// data.add(d1.getTime().getTime(), 100d);
		// d1.set(Calendar.DATE, 21);
		// data.add(d1.getTime().getTime(), 50d);
		// d1.set(Calendar.DATE, 22);
		// data.add(d1.getTime().getTime(), 75d);
		// d1.set(Calendar.DATE, 23);
		// data.add(d1.getTime().getTime(), 75d);

		// Calendar d1 = Calendar.getInstance();
		// d1.set(Calendar.HOUR, 0);
		// d1.set(Calendar.YEAR, 2013);
		// d1.set(Calendar.MONTH, Calendar.APRIL);
		// d1.set(Calendar.DATE, 20);
		//
		// DataTable data = new DataTable(Long.class, Double.class);
		// data.add(d1.getTime().getTime(), 100d);
		// d1.set(Calendar.DATE, 21);
		// data.add(d1.getTime().getTime(), 50d);
		// d1.set(Calendar.DATE, 22);
		// data.add(d1.getTime().getTime(), 75d);
		// d1.set(Calendar.DATE, 23);
		// data.add(d1.getTime().getTime(), 75d);

		// Create and format plot
		XYPlot plotLower = new XYPlot(data);
		Color colorLower = COLOR1;
		PointRenderer pointsLower = plotLower.getPointRenderer(data);
		pointsLower.setSetting(PointRenderer.COLOR, colorLower);
		pointsLower.setSetting(PointRenderer.SHAPE, new Ellipse2D.Double(-3, -3, 6, 6));
		pointsLower.setSetting(PointRenderer.VALUE_ALIGNMENT_Y, 1.2);
		pointsLower.setSetting(PointRenderer.VALUE_FONT, new Font("Arial", Font.PLAIN, 10));
		pointsLower.setSetting(PointRenderer.VALUE_DISPLAYED, true);
		LineRenderer lineLower = new DefaultLineRenderer2D();
		lineLower.setSetting(LineRenderer.STROKE, new BasicStroke(2f));
		lineLower.setSetting(LineRenderer.GAP, 1.0);
		lineLower.setSetting(LineRenderer.COLOR, colorLower);
		plotLower.setLineRenderer(data, lineLower);
		plotLower.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));
		PointRenderer pointRenderer = plotLower.getPointRenderer(data);
		Shape circle = new Ellipse2D.Double(-6.0, -6.0, 12.0, 12.0);
		pointRenderer.setSetting(PointRenderer.SHAPE, circle);
		// pointRenderer.setSetting(PointRenderer.VALUE_DISPLAYED, true);

		AreaRenderer areaUpper = new DefaultAreaRenderer2D();
		areaUpper.setSetting(AreaRenderer.COLOR, GraphicsUtils.deriveWithAlpha(colorLower, 64));
		plotLower.setAreaRenderer(data, areaUpper);
		plotLower.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));

		plotLower.setSetting(Plot.TITLE, "Daily Sales (Cash, Check & AR)");
		plotLower.setSetting(Plot.TITLE_FONT, new Font("Forte", Font.PLAIN, 18));

		// plotLower.setSetting(Plot.LEGEND, true);
		// plotLower.getLegend().setSetting(Legend.ORIENTATION,
		// Orientation.HORIZONTAL);

		plotLower.setInsets(new Insets2D.Double(15.0, 65.0, 50.0, 20.0));

		Column col1 = data.getColumn(0);
		Column col2 = data.getColumn(1);
		plotLower.getAxis(XYPlot.AXIS_Y).setRange(col2.getStatistics(Statistics.MIN) - 200, col2.getStatistics(Statistics.MAX) + 200);
		plotLower.getAxis(XYPlot.AXIS_X).setRange(col1.getStatistics(Statistics.MIN) - DateTool.DAY_DIFFERENCE,
				col1.getStatistics(Statistics.MAX) + DateTool.DAY_DIFFERENCE);

		Date lowerBound = DateTool.getDateWithoutTime(iss.get(iss.size() - 1).getDate());
		Date upperBound = DateTool.getDateWithoutTime(iss.get(0).getDate());

		// plotLower.getAxis(XYPlot.AXIS_Y).setMin(0d);

		long diff = upperBound.getTime() - lowerBound.getTime();

		// check if is interval is >= 20 days
		if (diff >= DateTool.DAY_DIFFERENCE * 20) {
			Date twentyDaysBefore = DateTool.getDaysBeforeDate(upperBound, 20);
			for (int endIndex = iss.size() - 2; endIndex >= 0; endIndex--) {
				InventorySheet is = iss.get(endIndex);
				if (is.getDate().after(twentyDaysBefore) || is.getDate().equals(twentyDaysBefore)) {
					// plotLower.getAxis(XYPlot.AXIS_X).setMin(iss.get(1).getDate().getTime());
					plotLower.getAxis(XYPlot.AXIS_X).setMin(is.getDate().getTime() - DateTool.DAY_DIFFERENCE);
					break;
				}
			}
		}

		plotLower.getAxis(XYPlot.AXIS_X).setMin(iss.get(1).getDate().getTime());

		DateFormat dateFormat = DateFormatter.getInstance().getFormat(Utility.DMYFormat);
		AxisRenderer rendererX = plotLower.getAxisRenderer(XYPlot.AXIS_X);
		rendererX.setSetting(AxisRenderer.TICK_LABELS_FORMAT, dateFormat);
		rendererX.setSetting(AxisRenderer.TICKS_SPACING, DateTool.DAY_DIFFERENCE * 2);
		rendererX.setSetting(AxisRenderer.TICKS_MINOR_COUNT, 1);
		rendererX.setSetting(AxisRenderer.TICKS_MINOR, true);
		rendererX.setSetting(AxisRenderer.TICKS_FONT, new Font("Arial", Font.PLAIN, 10));
		rendererX.setSetting(AxisRenderer.LABEL, "Date");

		AxisRenderer rendererY = plotLower.getAxisRenderer(XYPlot.AXIS_Y);
		rendererY.setSetting(AxisRenderer.TICKS_FONT, new Font("Arial", Font.PLAIN, 10));
		// rendererY.setSetting(AxisRenderer.TICKS_SPACING, 2000d);
		// rendererY.setSetting(AxisRenderer.TICKS_MINOR, true);
		rendererY.setSetting(AxisRenderer.LABEL, "Amount (PhP)");
		rendererY.setSetting(AxisRenderer.LABEL_DISTANCE, 2.0);

		DrawableContainer plots = new DrawableContainer(new TableLayout(1));
		plots.add(plotLower);

		InteractivePanel panel = new InteractivePanel(plots);
		add(panel);

		Values.linePlot = this;

	}

	// @SuppressWarnings("unchecked")
	// public LinePlot(String title, String description, String xAxisTitle,
	// String yAxisTitle, List<Date> dates, List<Double> values) throws Exception
	// {
	// super();
	// this.title = title;
	// this.description = description;
	// this.xAxisTitle = xAxisTitle;
	// this.yAxisTitle = yAxisTitle;
	// this.dates = dates;
	// this.values = values;
	//
	// if (dates.size() != values.size())
	// throw new Exception("Size of dates and values do not match!");
	//
	// DataTable data = new DataTable(Long.class, Double.class);
	// int i = 0;
	// for (Date d : dates) {
	// data.add(d.getTime(), values.get(i));
	// i++;
	// }
	//
	// XYPlot plotLower = new XYPlot(data);
	// // Format all number as time
	// AxisRenderer rendererX = plotLower.getAxisRenderer(XYPlot.AXIS_X);
	// DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DATE_FIELD);
	// rendererX.setSetting(AxisRenderer.TICK_LABELS_FORMAT, dateFormat);
	//
	// Color colorLower = COLOR1;
	// PointRenderer pointsLower = plotLower.getPointRenderer(data);
	// pointsLower.setSetting(PointRenderer.COLOR, colorLower);
	// pointsLower.setSetting(PointRenderer.SHAPE, new Ellipse2D.Double(-3, -3,
	// 6, 6));
	// LineRenderer lineLower = new DefaultLineRenderer2D();
	// lineLower.setSetting(LineRenderer.STROKE, new BasicStroke(2f));
	// lineLower.setSetting(LineRenderer.GAP, 1.0);
	// lineLower.setSetting(LineRenderer.COLOR, colorLower);
	// plotLower.setLineRenderer(data, lineLower);
	// plotLower.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));
	//
	// DrawableContainer plots = new DrawableContainer(new TableLayout(1));
	// // plots.add(plotUpper);
	// plots.add(plotLower);
	//
	// // Connect the two plots, i.e. user (mouse) actions affect both plots
	// // plotUpper.getNavigator().connect(plotLower.getNavigator());
	//
	// InteractivePanel panel = new InteractivePanel(plots);
	// add(panel);
	//
	//
	// Values.linePlot = this;
	// }

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String getxAxisTitle() {
		return xAxisTitle;
	}

	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}

	public String getyAxisTitle() {
		return yAxisTitle;
	}

	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// public static void main(String[] args) {
	// new LinePlot().showInFrame();
	// }
}
