import java.util.*

/**
 * Created by dragb on 07.01.2016.
 */
class AStar<TState> {
    fun solve(problem: Problem<TState>, heuristic: Heuristic<TState>): List<Action<TState>> {
        val closed: MutableMap<TState, StateEntry<TState>> = HashMap()
        val open: MutableMap<TState, StateEntry<TState>> = HashMap()

        val initialState = problem.getInitialState()
        val initialStateEntry = StateEntry<TState>(null, 0, heuristic.evaluate(initialState))
        open.put(initialState, initialStateEntry)

        while (open.isNotEmpty()) {
            val (state, entry) = open.minBy { it.value.f }!!

            // We reached our goal
            if (problem.isGoalState(state)) {
                return reconstructPath(state);
            }

            open.remove(state)
            closed.put(state, entry)

            problem.generateActions(state).map { Pair(it, it.apply(state)) }
        }
        throw NoSolutionFoundException()
    }

    private fun reconstructPath(targetState: TState): List<Action<TState>> {
        val path: MutableList<Action<TState>> = LinkedList()

        return path
    }
}

/* https://en.wikipedia.org/wiki/A*_search_algorithm
function A*(start,goal)
    ClosedSet := {}    	  // The set of nodes already evaluated.
    OpenSet := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
    Came_From := the empty map    // The map of navigated nodes.

    g_score := map with default value of Infinity
    g_score[start] := 0    // Cost from start along best known path.
    // Estimated total cost from start to goal through y.
    f_score := map with default value of Infinity
    f_score[start] := heuristic_cost_estimate(start, goal)

    while OpenSet is not empty
        current := the node in OpenSet having the lowest f_score[] value
        if current = goal
            return reconstruct_path(Came_From, goal)

        OpenSet.Remove(current)
        ClosedSet.Add(current)
        for each neighbor of current
            if neighbor in ClosedSet
                continue		// Ignore the neighbor which is already evaluated.
            tentative_g_score := g_score[current] + dist_between(current,neighbor) // length of this path.
            if neighbor not in OpenSet	// Discover a new node
                OpenSet.Add(neighbor)
            else if tentative_g_score >= g_score[neighbor]
                continue		// This is not a better path.

            // This path is the best until now. Record it!
            Came_From[neighbor] := current
            g_score[neighbor] := tentative_g_score
            f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)

    return failure

function reconstruct_path(Came_From,current)
    total_path := [current]
    while current in Came_From.Keys:
        current := Came_From[current]
        total_path.append(current)
    return total_path
*/