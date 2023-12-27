SUMMARY = "A dotnet debug console-only image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

include stm32mp-image.inc

# Adds 2GB extra free space to the root filesystem
IMAGE_ROOTFS_EXTRA_SPACE = "2097152"

# Disable Secure Boot signing
SECBOOT_SIGN ?= "0"

# Additional application configuration
CORE_IMAGE_EXTRA_INSTALL += " \
    lrzsz \
    packagegroup-tinyos-dotnet-debug \
"