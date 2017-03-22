package de.thaso.jd.db.common;

/**
 * ApplicationException
 *
 * @author thaler
 * @since 2017-03-22
 */
public class DatabaseException extends RuntimeException {

    private DatabaseError databaseError;

    public DatabaseException(final DatabaseError databaseError, final String message) {
        super(message);
        this.databaseError = databaseError;
    }

    public DatabaseError getDatabaseError() {
        return databaseError;
    }
}
