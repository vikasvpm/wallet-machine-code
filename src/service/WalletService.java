package service;

import entity.BusinessWallet;
import entity.PersonalWallet;
import entity.Wallet;
import exception.WalletException;
import respository.WalletRepository;
import service.validation.ValidationService;

import java.util.Map;
import java.util.Objects;

public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Boolean createWallet(String username, String gst, Integer amount) {
        try {
            ValidationService.checkForDuplicate(username, walletRepository.getDbMap());
            BusinessWallet businessWallet = new BusinessWallet();
            businessWallet.setUsername(username);
            businessWallet.setBalanceAmount(amount);
            businessWallet.setGst(gst);
            walletRepository.getDbMap().put(username, businessWallet);
            return true;
        }
        catch (WalletException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public Boolean createWallet(String username, Integer amount) {
        try {
            ValidationService.checkForDuplicate(username, walletRepository.getDbMap());
            ValidationService.checkMinLimitForPersonal(amount);
            PersonalWallet personalWallet = new PersonalWallet();
            personalWallet.setUsername(username);
            personalWallet.setBalanceAmount(amount);
            walletRepository.getDbMap().put(username, personalWallet);
            return true;
        }
        catch (WalletException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public void overview() {
        Map<String, Wallet> overviewMap = walletRepository.getDbMap();
        if(Objects.equals(overviewMap.size(), 0)) {
            System.out.println("No Accounts present yet in the system.");
        }
        int index = 1;
        for(Map.Entry<String, Wallet> entry : overviewMap.entrySet()) {
            System.out.println("Entry " + index++);
            System.out.println("Account Holder : " + entry.getKey());
            System.out.println("Account Type : " + ((entry.getValue() instanceof PersonalWallet) ? "Personal" : "Business"));
            System.out.println("Balance : " + entry.getValue().getBalanceAmount());
            System.out.println();
        }
    }

    public void displayUserBalance(String username) {
        try {
            ValidationService.checkUserExists(username, walletRepository.getDbMap());
            Wallet wallet = walletRepository.getDbMap().get(username);
            System.out.println("Account Holder : " + username);
            System.out.println("Account Type : " + ((wallet instanceof PersonalWallet) ? "Personal" : "Business"));
            System.out.println("Balance : " + wallet.getBalanceAmount());
        }
        catch (WalletException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
