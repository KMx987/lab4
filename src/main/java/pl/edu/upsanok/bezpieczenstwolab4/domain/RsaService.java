package pl.edu.upsanok.bezpieczenstwolab4.domain;

import java.math.BigInteger;
import org.springframework.stereotype.Service;

@Service
public class RsaService {

  private PrivateKey privateKey;

  public RsaService() {}

  public PublicKey generate(BigInteger p, BigInteger q) {
    BigInteger n = BigInteger.ONE; //FIXME
    BigInteger phi = BigInteger.ONE; //FIXME
    BigInteger e = BigInteger.ONE; //FIXME
    BigInteger d = BigInteger.ONE; // FIXME
    this.privateKey = new PrivateKey(d, n);
    return new PublicKey(e, n);
  }

  public String encrypt(long numer, PublicKey publicKey){
    if (publicKey != null) {
      BigInteger c = BigInteger.ONE; // FIXME
      return c.toString();
    } else {
      return "Zadanie nie zaczęte";
    }
  }

  public BigInteger decrypt(BigInteger encryptedNumber){
    return BigInteger.ONE; // FIXME
  }

  public record PublicKey(BigInteger e, BigInteger n){}
  private record PrivateKey(BigInteger d, BigInteger n){}
}
