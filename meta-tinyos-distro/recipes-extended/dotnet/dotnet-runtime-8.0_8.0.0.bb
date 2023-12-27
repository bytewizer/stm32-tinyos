DESCRIPTION = "Microsoft .NET Core 8.0 .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DOTNET_FETCH_ID = "ee0a9245-5b87-4217-a9a5-dc589187612e/f3631cfe19cb713296314a6028a9e856"

SRC_URI[sha256sum] = "57dda8db012344c4d6a7f25db564bc4cb32c6b158e7daedb9532c6204015f386"
SRC_URI = " \
    https://download.visualstudio.microsoft.com/download/pr/${DOTNET_FETCH_ID}/dotnet-runtime-${PV}-linux-arm.tar.gz;unpack=0 \
    file://dotnet-runtime.sh \
"

COMPATIBLE_HOST ?= "(arm).*-linux"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = '1' 

DEPENDS += "\
    zlib \
"

RDEPENDS:${PN} = "\
    glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
    openssl \
"

FILES:${PN} += "\
    ${datadir}/dotnet \
"

# FILES:${PN}-dev = "\
#     ${datadir}/dotnet/sdk \
#     ${datadir}/dotnet/sdk-manifests \
#     ${datadir}/dotnet/templates \
# "

# FILES:${PN}-dbg = "\
#     ${datadir}/dotnet/.debug \
# "

INSANE_SKIP:${PN} = "file-rdeps staticdev libdir"
INSANE_SKIP:${PN}-dbg = "libdir"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    
    install -d ${D}${datadir}/dotnet
    tar --no-same-owner -xpvzf ${WORKDIR}/dotnet-runtime-${PV}-linux-arm.tar.gz -C ${D}${datadir}/dotnet
    chmod +x ${D}${datadir}/dotnet/dotnet

    # Symlinks
    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/dotnet/dotnet ${D}${bindir}/dotnet
}

do_install:append() {
    
    install -d ${D}${sysconfdir}/profile.d
    install -m 644 ${WORKDIR}/dotnet-runtime.sh ${D}${sysconfdir}/profile.d
}