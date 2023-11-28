
/**
 * @overview Displays a text-based tabular report on some {@link PC} objects.
 */
public class PCReport {

    /**
     * @effects
     *
     *          <pre>
     *  generate and return a tabular report about PC objects in this.
     *
     *  The report title is displayed in the middle of the top banner.
     *
     *  All but the first column correspond to the PC attributes, the rows are data about the PC objects. Thus, the second column corresponds to attribute model, the third corresponds to year, and so on.
     *  The last column lists the string representations of the components of the PC objects. The first column sequentially displays the row numbers.
     *  Note that the widths of second and fourth columns are lengths of the corresponding attributes. Widths of the first, third and fifth columns are 3, 6 and 50 (respectively).
     *
     *  The cell values are properly aligned with the columns and are displayed right-justified. The cell values need not be wrapped.
     *  Further, boundaries of two adjacent cells on same row are exactly one space (� �) apart.
     *          </pre>
     */
    public String displayReport(PC[] pcs) {
        StringBuffer report = new StringBuffer();
        for (int i = 0; i < 103; i++)
            report.append("-");
        report.append("\n");
        for (int i = 0; i < 47; i++)
            report.append(" ");
        report.append("PCPROG REPORT").append("\n");
        for (int i = 0; i < 103; i++)
            report.append("-");
        report.append("\n");
        String indexFormat = "%3d";
        String modelFormat = "%20s";
        String yearFormat = "%6d";
        String manufacturerFormat = "%20s";
        String compsFormat = "%50s";
        for (int i = 0; i < pcs.length; i++) {
            PC pc = pcs[i];
            report.append(String.format(indexFormat, i + 1)).append(" ")
                    .append(String.format(modelFormat, pc.getModel())).append(" ")
                    .append(String.format(yearFormat, pc.getYear())).append(" ")
                    .append(String.format(manufacturerFormat, pc.getManufacturer())).append(" ")
                    .append(String.format(compsFormat, pc.getComps().getElements().toString())).append("\n");
        }

        for (int i = 0; i < 103; i++)
            report.append("-");
        return report.toString();
    }
}
