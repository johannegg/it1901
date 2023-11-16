package ui;

abstract class DataAccessController {

    private DataAccess dataAccess = new RemoteDataAccess();

    /**
     * Sets data access instance.
     *
     * @param dataAccess Direct- or RemoteDataAccess instance.
     */
    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Gets the instance of DataAccess.
     *
     * @return DataAccess.
     */
    public DataAccess getDataAccess() {
        return this.dataAccess;
    }
}
