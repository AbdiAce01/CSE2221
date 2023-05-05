import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        Set<T> temp = s.newInstance();
        Set<T> result = s.newInstance();
        temp.transferFrom(s);
        int size = temp.size();
        while (size > 0) {
            T x = temp.removeAny();
            if (this.contains(x)) {
                this.remove(x);
                s.add(x);
                result.add(x);
            } else {
                s.add(x);
            }
            size--;
        }
        return result;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        Set<T> temp = s.newInstance();
        temp.transferFrom(s);
        int size = temp.size();
        while (size > 0) {
            T x = temp.removeAny();
            if (!this.contains(x)) {
                this.add(x);
            } else {
                s.add(x);
            }
            size--;
        }

    }

}