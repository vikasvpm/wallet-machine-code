package service;

import constants.Constants;
import entity.BusinessWallet;
import entity.PersonalWallet;
import entity.Wallet;
import exception.WalletException;
import respository.WalletRepository;
import service.validation.ValidationService;

import java.util.Objects;

public class TransactionService {
    private WalletRepository walletRepository;

    public TransactionService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Boolean transferMoney(String sender, String receiver, Integer amount) {
        try {
            ValidationService.checkMinTransferValue(amount);
            ValidationService.checkUserExists(sender, walletRepository.getDbMap());
            ValidationService.checkUserExists(receiver, walletRepository.getDbMap());
            Wallet senderWallet = walletRepository.getDbMap().get(sender);
            Wallet receiverWallet = walletRepository.getDbMap().get(receiver);
            ValidationService.validateTransferConditions(senderWallet, receiverWallet, amount);
            senderWallet.decreaseAmount(amount);
            receiverWallet.increaseAmount(amount);
            if(Objects.equals(senderWallet.getBalanceAmount(), receiverWallet.getBalanceAmount())) {
                senderWallet.increaseAmount(Constants.REWARD_AMOUNT);
                receiverWallet.increaseAmount(Constants.REWARD_AMOUNT);
            }
        }
        catch(WalletException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }


}
