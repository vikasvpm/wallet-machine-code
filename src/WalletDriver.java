import respository.WalletRepository;
import service.TransactionService;
import service.WalletService;

public class WalletDriver {
    public static void main(String[] args) {

        WalletRepository walletRepository = new WalletRepository();
        WalletService walletService = new WalletService(walletRepository);
        TransactionService transactionService = new TransactionService(walletRepository);

        walletService.createWallet("vikas", 100);
        walletService.createWallet("shruti", 150);
        walletService.createWallet("evilCorp", "sd", 150);
        walletService.overview();

        transactionService.transferMoney("shruti", "vikas", 25);
        walletService.overview();

        transactionService.transferMoney("evilCorp", "vikas", 25);

        transactionService.transferMoney("vikas", "evilCorp", 45);

        walletService.overview();
    }
}