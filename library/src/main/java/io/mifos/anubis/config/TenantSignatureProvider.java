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
package io.mifos.anubis.config;


import io.mifos.anubis.api.v1.domain.Signature;

import java.util.Optional;

public interface TenantSignatureProvider {
  /**
   *
   * @param version The version of the signature to get.
   * @return The public keys that the identity service uses for signing tokens.
   * @throws IllegalArgumentException if the tenant context is not set.
   */
  Optional<Signature> getSignature(String version) throws IllegalArgumentException;
}
