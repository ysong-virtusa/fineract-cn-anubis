/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.anubis.provider;

import io.mifos.core.lang.security.RsaPublicKeyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.PublicKey;

/**
 * @author Myrle Krantz
 */
@Component
public class SystemRsaKeyProvider {
  private String systemPublicKeyMod;
  private String systemPublicKeyExp;

  private PublicKey systemPublicKey;

  @Autowired
  public SystemRsaKeyProvider(final @Value("${system.publicKey.modulus}") String systemPublicKeyMod, final @Value("${system.publicKey.exponent}") String systemPublicKeyExp)
  {
    this.systemPublicKeyMod = systemPublicKeyMod;
    this.systemPublicKeyExp = systemPublicKeyExp;
  }

  @PostConstruct
  public void init() {
    this.systemPublicKey =
        new RsaPublicKeyBuilder()
            .setPublicKeyMod(new BigInteger(systemPublicKeyMod))
            .setPublicKeyExp(new BigInteger(systemPublicKeyExp))
            .build();
  }

  public PublicKey getPublicKey(final String tokenVersion) throws InvalidKeyVersionException {
    if (!tokenVersion.equals("1"))
      throw new InvalidKeyVersionException(tokenVersion);
    return systemPublicKey;
  }
}
