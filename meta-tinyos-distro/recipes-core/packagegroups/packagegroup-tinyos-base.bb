DESCRIPTION = "Base application packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-core \
	packagegroup-tinyos-udev \
"

RDEPENDS:${PN} = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-core \
	packagegroup-tinyos-udev \
"

RDEPENDS:packagegroup-tinyos-core = "\
	watchdog \
	sudo \
	glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
	openssl"

RDEPENDS:packagegroup-tinyos-udev = "\
    udev-rules-i2c \
    udev-rules-spi \
    udev-rules-gpio \
    udev-rules-video \
    udev-rules-serial \
"