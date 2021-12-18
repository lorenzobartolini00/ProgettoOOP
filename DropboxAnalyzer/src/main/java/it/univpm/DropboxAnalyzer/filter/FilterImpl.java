package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;
import java.util.function.Predicate;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public abstract class FilterImpl implements Filter {
	protected Long minSize;
	protected Long maxSize;

	public Long getMinSize() {
		return minSize;
	}

	public void setMinSize(Long minSize) {
		this.minSize = minSize;
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public void setFilters(Map<String, Object> parameters) {
		if(parameters.containsKey("filters"))
		{
			Map<String, Object> filters = (Map<String, Object>) parameters.get("filters");
			
			if(filters.containsKey("min_size"))
			{
				this.setMinSize(Integer.toUnsignedLong((Integer)filters.get("min_size")));
			}
			if(filters.containsKey("max_size"))
			{
				this.setMaxSize(Integer.toUnsignedLong((Integer)filters.get("max_size"))) ;
			}
		}
	}

	@Override
	public abstract void applyFilters();

}
