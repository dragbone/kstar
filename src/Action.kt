/**
 * Created by dragb on 07.01.2016.
 */
interface Action<TState> {
    fun apply(state: TState): TState
}