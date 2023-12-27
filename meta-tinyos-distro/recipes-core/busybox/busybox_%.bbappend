INITSCRIPT_PACKAGES += "${PN}-ifplugd"
INITSCRIPT_NAME_${PN}-ifplugd = "busybox-ifplugd"
INITSCRIPT_PARAMS_${PN}-ifplugd = "defaults 5 75"

inherit update-rc.d

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://i2c-enable.cfg \
    file://ifplugd-enable.cfg \
    file://less-enable.cfg \
    file://mdev-enable.cfg \
    file://ntpd-enable.cfg \
    file://stm32-enable.cfg \
    file://telnet-disable.cfg \
    file://tftp-disable.cfg \
    file://udhcpd-enable.cfg \
    file://wget-enable.cfg \
    file://ifplugd.cfg \
    file://ifplugd.default \
    file://ifplugd.ifupdown \
    file://ifplugd.init \
    file://udhcpd.conf \
    "

do_install:append() {
    install -d ${D}/${sysconfdir}
    install -d ${D}/${sysconfdir}/default
    install -d ${D}/${sysconfdir}/ifplugd/action.d

    install -m 644 ${WORKDIR}/ifplugd.default \
        ${D}/${sysconfdir}/default/ifplugd
    install -m 755 ${WORKDIR}/ifplugd.ifupdown \
        ${D}/${sysconfdir}/ifplugd/action.d/ifupdown
    install -m 755 ${WORKDIR}/ifplugd.init \
        ${D}/${sysconfdir}/init.d/busybox-ifplugd
    install -m 644 ${WORKDIR}/udhcpd.conf ${D}/${sysconfdir}
}

FILES_${PN}-udhcpd += " \
    ${sysconfdir}/udhcpd.conf \
    "

CONFFILES_${PN}-udhcpd += " \
    ${sysconfdir}/udhcpd.conf \
    "

PACKAGE_BEFORE_PN += "${PN}-ifplugd"

FILES_${PN}-ifplugd += " \
    ${sysconfdir}/default/ifplugd \
    ${sysconfdir}/ifplugd \
    ${sysconfdir}/init.d/busybox-ifplugd \
    "