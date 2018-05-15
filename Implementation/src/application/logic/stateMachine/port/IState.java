package application.logic.stateMachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IState {
	
	public boolean isSubStateOf(IState state);

	public boolean isSuperStateOf(IState state);

	public enum State implements IState {

		Started, ValidationFailed, Login(Started, ValidationFailed), //
		SelectUseCase, EditMember, NotAllowed, ManageMembers(EditMember, NotAllowed), //
		Initialized(SelectUseCase, ManageMembers);

		/**
		 * @clientNavigability NAVIGABLE
		 * @directed true
		 * @supplierRole subState
		 */

		private List<IState> subStates;

		private State(IState... subS) {
			this.subStates = new ArrayList<>(Arrays.asList(subS));
		}

		@Override
		public boolean isSuperStateOf(IState s) {
			boolean result = s == null || this == s; // self contained
			for (IState state : this.subStates) // or
				result |= state.isSuperStateOf(s); // contained in a substate!
			return result;
		}

		@Override
		public boolean isSubStateOf(IState state) {
			return (state == null) ? false : state.isSuperStateOf(this);
		}
	}
	
}
