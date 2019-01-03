package server_side;

import java.util.HashMap;


public class FileCacheManager<Problem,Solution> implements CacheManager<Problem,Solution> {

	public HashMap<Problem,Solution> solutionMap = new HashMap<>();
	
	
	@Override
	public boolean isSolutionCached(Problem p) {
		if(solutionMap.containsKey(p)) {
			return true;
		}
		return false;
	}

	@Override
	public Solution getSolution(Problem p) {
			return solutionMap.get(p);
	}

	@Override
	public void saveSolution(Problem p, Solution s) {
			solutionMap.put(p,s);
	}

	
}
