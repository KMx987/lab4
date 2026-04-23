package pl.edu.upsanok.bezpieczenstwolab4.domain;
import java.math.BigInteger;
import org.springframework.stereotype.Service;

@Service
public class RsaService {

    private PrivateKey privateKey;
    public RsaService() {}
    public PublicKey generate(BigInteger p, BigInteger q) {

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(7);
        BigInteger d = e.modInverse(phi);
        this.privateKey = new PrivateKey(d, n);
        return new PublicKey(e, n);
    }

    public String encrypt(long numer, PublicKey publicKey){
        if (publicKey != null) {
            BigInteger number = BigInteger.valueOf(numer);
            BigInteger c = number.modPow(publicKey.e(), publicKey.n());
            return c.toString();
        } else {
            return "Zadanie nie zaczęte";
        }
    }

    public BigInteger decrypt(BigInteger encryptedNumber){
        return encryptedNumber.modPow(privateKey.d(), privateKey.n());
    }

    public record PublicKey(BigInteger e, BigInteger n){}
    private record PrivateKey(BigInteger d, BigInteger n){}
}