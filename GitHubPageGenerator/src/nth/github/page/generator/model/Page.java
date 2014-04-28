package nth.github.page.generator.model;

import java.net.URI;


public interface Page {
	public String getTitle();

	public String getPath();

	public String toString();

	public void setPreviousPage(Page previousPage);

	public void setHomePage(Page homePage);

	public void setNextPage(Page nextPage);

}
