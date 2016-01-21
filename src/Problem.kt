/**
 * Created by dragb on 07.01.2016.
 */
interface Problem<TState> {
    fun generateActions(state: TState): List<Action<TState>>
    fun getInitialState(): TState
    fun isGoalState(state: TState): Boolean
}