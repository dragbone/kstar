/**
 * Created by dragb on 07.01.2016.
 */
interface Heuristic<TState> {
    fun evaluate(state: TState): Int
}