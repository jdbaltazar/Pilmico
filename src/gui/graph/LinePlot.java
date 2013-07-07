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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.erichseifert.gral.data.Column;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.graphics.DrawableContainer;
import de.erichseifert.gral.graphics.Layout;
import de.erichseifert.gral.graphics.TableLayout;
import de.erichseifert.gral.plots.Plot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.legends.Legend;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;
import de.erichseifert.gral.util.Orientation;

public class LinePlot extends ExamplePanel {
	/** Version id for serialization. */
	private static final long serialVersionUID = 6832343098989019088L;

	private String title;
	private String description;
	private String xAxisTitle;
	private String yAxisTitle;
	private List<Date> dates;
	private List<Double> values;

	@SuppressWarnings("unchecked")
	public LinePlot() {

		// better graph title appearance
		// x and y axes labels
		// show data(x, y) values when hovered

		Calendar d1 = Calendar.getInstance();
		d1.set(Calendar.HOUR, 0);
		d1.set(Calendar.YEAR, 2013);
		d1.set(Calendar.MONTH, Calendar.APRIL);
		d1.set(Calendar.DATE, 20);

		DataTable data = new DataTable(Long.class, Double.class);
		data.add(d1.getTime().getTime(), 100d);
		d1.set(Calendar.DATE, 21);
		data.add(d1.getTime().getTime(), 50d);
		d1.set(Calendar.DATE, 22);
		data.add(d1.getTime().getTime(), 75d);
		d1.set(Calendar.DATE, 23);
		data.add(d1.getTime().getTime(), 75d);

		// Create and format plot
		XYPlot plotLower = new XYPlot(data);
		Color colorLower = COLOR1;
		PointRenderer pointsLower = plotLower.getPointRenderer(data);
		pointsLower.setSetting(PointRenderer.COLOR, colorLower);
		pointsLower.setSetting(PointRenderer.SHAPE, new Ellipse2D.Double(-3, -3, 6, 6));
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

		plotLower.setSetting(Plot.TITLE, "Pilmico");
		// insert Pilmico Font here
		// plotLower.setSetting(Plot.TITLE_FONT, n);
		// plotLower.setSetting(Plot.LEGEND, true);
		// plotLower.getLegend().setSetting(Legend.ORIENTATION,
		// Orientation.HORIZONTAL);
		plotLower.getAxis(XYPlot.AXIS_Y).setMin(0d);
		AxisRenderer rendererY = plotLower.getAxisRenderer(XYPlot.AXIS_Y);

		
		// rendererY.setSetting(AxisRenderer., arg1);
		// rendererY.
		Column col1 = data.getColumn(0);
		plotLower.getAxis(XYPlot.AXIS_X).setRange(col1.getStatistics(Statistics.MIN), col1.getStatistics(Statistics.MAX));

		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
		AxisRenderer rendererX = plotLower.getAxisRenderer(XYPlot.AXIS_X);
		rendererX.setSetting(AxisRenderer.TICK_LABELS_FORMAT, dateFormat);
		// rendererX.setSetting(AxisRenderer.LABEL, "Date");

		DrawableContainer plots = new DrawableContainer(new TableLayout(1));
		plots.add(plotLower);

		InteractivePanel panel = new InteractivePanel(plots);
		add(panel);
	}

	@SuppressWarnings("unchecked")
	public LinePlot(String title, String description, String xAxisTitle, String yAxisTitle, List<Date> dates, List<Double> values) throws Exception {
		super();
		this.title = title;
		this.description = description;
		this.xAxisTitle = xAxisTitle;
		this.yAxisTitle = yAxisTitle;
		this.dates = dates;
		this.values = values;

		if (dates.size() != values.size())
			throw new Exception("Size of dates and values do not match!");

		DataTable data = new DataTable(Long.class, Double.class);
		int i = 0;
		for (Date d : dates) {
			data.add(d.getTime(), values.get(i));
			i++;
		}

		XYPlot plotLower = new XYPlot(data);
		// Format all number as time
		AxisRenderer rendererX = plotLower.getAxisRenderer(XYPlot.AXIS_X);
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DATE_FIELD);
		rendererX.setSetting(AxisRenderer.TICK_LABELS_FORMAT, dateFormat);

		Color colorLower = COLOR1;
		PointRenderer pointsLower = plotLower.getPointRenderer(data);
		pointsLower.setSetting(PointRenderer.COLOR, colorLower);
		pointsLower.setSetting(PointRenderer.SHAPE, new Ellipse2D.Double(-3, -3, 6, 6));
		LineRenderer lineLower = new DefaultLineRenderer2D();
		lineLower.setSetting(LineRenderer.STROKE, new BasicStroke(2f));
		lineLower.setSetting(LineRenderer.GAP, 1.0);
		lineLower.setSetting(LineRenderer.COLOR, colorLower);
		plotLower.setLineRenderer(data, lineLower);
		plotLower.setInsets(new Insets2D.Double(20.0, 50.0, 40.0, 20.0));

		DrawableContainer plots = new DrawableContainer(new TableLayout(1));
		// plots.add(plotUpper);
		plots.add(plotLower);

		// Connect the two plots, i.e. user (mouse) actions affect both plots
		// plotUpper.getNavigator().connect(plotLower.getNavigator());

		InteractivePanel panel = new InteractivePanel(plots);
		add(panel);
	}

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

	public static void main(String[] args) {
		new LinePlot().showInFrame();
	}
}
