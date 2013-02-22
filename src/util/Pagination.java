package util;
/**
 * 分页类
 * @author Administrator
 *
 */
public class Pagination {
   
    private int intcurrentPage=1;

    private int nextPage;
    private int previousPage;
    private int maxPage;
    //每页多少条记录
    private int perPage=5;
	public int getIntcurrentPage() {
		return intcurrentPage;
	}
	public void setIntcurrentPage(int intcurrentPage) {
		this.intcurrentPage = intcurrentPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	
    
}