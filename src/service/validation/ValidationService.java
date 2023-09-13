package service.validation;

import constants.Constants;
import entity.BusinessWallet;
import entity.PersonalWallet;
import entity.Wallet;
import exception.WalletException;

import java.util.Map;

public class ValidationService {
    public static void checkForDuplicate(String username, Map<String, Wallet> dbMap) throws WalletException {
        if(dbMap.containsKey(username)) {
            throw new WalletException("Username : " + username + " is already taken, please use different name");
        }
    }

    public static void checkMinLimitForPersonal(Integer amount) throws WalletException {
        if(amount < Constants.MIN_PERSONAL_LIMIT) {
            throw new WalletException("Minimum balance for personal account is FKR 50");
        }
    }

    public static void checkMinTransferValue(Integer amount) throws WalletException {
        if(amount < Constants.MIN_TRANSACTION_LIMIT) throw new WalletException("Smallest possible transfer is FKR 1");
    }

    public static void validateTransferConditions(Wallet senderWallet, Wallet receiverWallet, Integer amount) throws WalletException {
        if ((senderWallet instanceof BusinessWallet) && (receiverWallet instanceof PersonalWallet)) {
            throw new WalletException("Cannot transfer Money from Business Wallet to Personal Wallet");
        }
        if(senderWallet instanceof PersonalWallet) {
            if(senderWallet.getBalanceAmount() - amount < Constants.MIN_PERSONAL_LIMIT) {
                throw new WalletException("Cannot transfer money because of low account balance");
            }
        }
        else {
            if(senderWallet.getBalanceAmount() - amount < Constants.MIN_BUSINESS_LIMIT) {
                throw new WalletException("Cannot transfer money because of low account balance");
            }
        }
    }

    public static void checkUserExists(String username, Map<String, Wallet> dbMap) throws WalletException {
        if(!dbMap.containsKey(username)) {
            throw new WalletException("No user exists with username :" + username);
        }
    }

    public void checkDuplicateUserName() {

    }
}
