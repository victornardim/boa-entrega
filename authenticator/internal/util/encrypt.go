package util

import (
	"crypto/sha256"
	"encoding/hex"
)

func Encrypt(value string) string {
	hash := sha256.Sum256([]byte(value))
	encrypted := hex.EncodeToString(hash[:])

	return encrypted
}
