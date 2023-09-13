package respository;

import entity.Wallet;

import java.util.HashMap;
import java.util.Map;

public class WalletRepository {
    private Map<String, Wallet> dbMap;
    public WalletRepository() {
        this.dbMap = new HashMap<>();
    }

    public Map<String, Wallet> getDbMap() {
        return dbMap;
    }

    public void setDbMap(Map<String, Wallet> dbMap) {
        this.dbMap = dbMap;
    }

}
