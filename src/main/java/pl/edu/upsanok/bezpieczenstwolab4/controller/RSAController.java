package pl.edu.upsanok.bezpieczenstwolab4.controller;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.edu.upsanok.bezpieczenstwolab4.domain.RsaService;

@Controller
@SessionAttributes({"result1", "result2", "value1", "value2", "publicKey"})
public class RSAController {

  private RsaService rsaService;

  public RSAController(@Autowired RsaService rsaService) {
    this.rsaService = rsaService;
  }

  @GetMapping("/rsa")
  public String showForm(Model model) {

    if (!model.containsAttribute("result1")) {
      model.addAttribute("result1", null);
    }
    if (!model.containsAttribute("result2")) {
      model.addAttribute("result2", null);
    }
    return "rsa";
  }

    @PostMapping("/rsa-generate")
    public String generate(Model model) {
        BigInteger p = BigInteger.probablePrime(128, new java.util.Random());
        BigInteger q = BigInteger.probablePrime(128, new java.util.Random());

        var publicKey = rsaService.generate(p, q);
        model.addAttribute("publicKey", publicKey);
        return "rsa";
    }

  @PostMapping("/rsaenc")
  public String calc1(@RequestParam int toBeEncrypted, Model model) {
    RsaService.PublicKey publicKey = (RsaService.PublicKey) model.getAttribute("publicKey");
    String encrypted = rsaService.encrypt(toBeEncrypted, publicKey);
    model.addAttribute("originalValue", toBeEncrypted);
    model.addAttribute("encryptedValue", encrypted);
    return "rsa";
  }

  @PostMapping("/rsadec")
  public String calc2(@RequestParam BigInteger encryptedValue, Model model) {
    BigInteger decrypted = rsaService.decrypt(encryptedValue);
    model.addAttribute("decryptedValue", decrypted);
    model.addAttribute("encryptedValue", encryptedValue);

    return "rsa";
  }
}
